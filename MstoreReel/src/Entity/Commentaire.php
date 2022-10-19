<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire")
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_comment", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idComment;

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

    /**
     * @var string
     *
     * @ORM\Column(name="body", type="text", length=65535, nullable=false)
     */
    private $body;

    /**
     * @var string
     *
     * @ORM\Column(name="date", type="string", length=30, nullable=false)
     */
    private $date;


}
