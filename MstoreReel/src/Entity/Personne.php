<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;






/**
 * Personne
 * @ORM\Table(name="personne", uniqueConstraints={@ORM\UniqueConstraint(name="email", columns={"email"}), @ORM\UniqueConstraint(name="username", columns={"username"})})
 * @ORM\Entity(repositoryClass=PersonneRepository::class)
 * @UniqueEntity(
 * fields= {"email"},
 * message="L'email que vous avez indiqué est déjà utilisé !"
 * )
 * * @UniqueEntity(
 * fields= {"username"},
 * message="Le Nom d'utilisateur que vous avez indiqué est déjà utilisé !"
 * )
 * @ORM\Entity
 */
class Personne implements UserInterface
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=30, nullable=false)
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="role", type="string", length=30, nullable=false)
     */
    private $role;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=250, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=100, nullable=false)
     */
    private $username;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=100, nullable=false)
     * @Assert\Length(min="6" , minMessage="Votre mot de passe doit faire minimum 6 caractéres")
     */
    private $password;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Please upload image")
     * @Assert\File(mimeTypes={"image/jpeg"})
     */
    private $photo ;

    /**
     * @var string
     *
     * @ORM\Column(name="num_telephone", type="string", length=8, nullable=false)
     */
    private $numTelephone;


    public function construct()
    {

        $this->nom = new ArrayCollection();
        $this->prenom = new ArrayCollection();
        $this->role = new ArrayCollection();
        $this->email = new ArrayCollection();;
        $this->username = new ArrayCollection();
        $this->password = new ArrayCollection();
        $this->photo = new ArrayCollection();
        $this->numTelephone = new ArrayCollection();
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom(string $prenom): void
    {
        $this->prenom = $prenom;
    }

    /**
     * @return string
     */
    public function getRole(): ?string
    {
        return $this->role;
    }

    /**
     * @param string $role
     */
    public function setRole(string $role): void
    {
        $this->role = $role;
    }

    /**
     * @return string
     */
    public function getEmail(): ?string
    {
        return $this->email;
    }

    /**
     * @param string $email
     */
    public function setEmail(string $email): void
    {
        $this->email = $email;
    }

    /**
     * @return string
     */
    public function getUsername(): ?string
    {
        return $this->username;
    }

    /**
     * @param string $username
     */
    public function setUsername(string $username): void
    {
        $this->username = $username;
    }

    /**
     * @return string
     */
    public function getPassword(): ?string
    {
        return $this->password;
    }

    /**
     * @param string $password
     */
    public function setPassword(string $password): void
    {
        $this->password = $password;
    }

    /**
     * @return mixed
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param mixed $image
     */
    public function setPhoto($photo): void
    {
        $this->photo = $photo;
    }

    /**
     * @return string
     */
    public function getNumTelephone(): ?string
    {
        return $this->numTelephone;
    }

    /**
     * @param string $numTelephone
     */
    public function setNumTelephone(string $numTelephone): void
    {
        $this->numTelephone = $numTelephone;
    }
    /**
     *@Assert\EqualTo(propertyPath="password" , message="Vous n'avez pas tapez la même mot de passe")
     */
    public $confirm_password;

    public function getRoles()
    {
        return ['ROLE_USER'];
    }
    public function getSalt()
    {

    }
    public function eraseCredentials()
    {

    } /*
    public function serialize()
    {
        return serialize([
            $this->id,

            $this->username,

            $this->numTelephone,
            $this->prenom,
            $this->nom,
            $this->photo,
            $this->role,
            $this->email,
            $this->password
        ]);
    }
    public function unserialize($string)
{
List (

$this->id,

$this->username,

$this->numTelephone,
$this->prenom,
$this->nom,
$this->photo,
$this->role,
$this->email,
$this->password
) = unserialize(string ,['allowed_classes' => false]);
}

*/
}
