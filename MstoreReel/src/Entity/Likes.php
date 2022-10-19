<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Likes
 *
 * @ORM\Table(name="likes")
 * @ORM\Entity
 */
class Likes
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_like", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idLike;

    /**
     * @var int
     *
     * @ORM\Column(name="id_actu", type="integer", nullable=false)
     */
    private $idActu;

    /**
     * @var int
     *
     * @ORM\Column(name="id_perso", type="integer", nullable=false)
     */
    private $idPerso;


}
